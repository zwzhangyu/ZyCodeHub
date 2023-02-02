package com.ruoyi.framework.shiro.web.filter.sensitive;

import com.alibaba.fastjson.JSON;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/***
 *
 *  敏感词过滤
 * @author ZhangYu
 * @date 2021/7/16
 */
@Component
public class SensitiveParameterFilter implements Filter {

    private static final List<String> FILTER_METHOD = Arrays.asList("POST", "PUT");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, IllegalArgumentException, ServletException {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        //现只对POST和PUT请求参数过滤
        if (FILTER_METHOD.contains(method)) {
            for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
                Set<String> sensitiveWord = getSensitiveWord(JSON.toJSONString(stringEntry.getValue()), 1);
                if (sensitiveWord.size() > 0) {
                    servletResponse.setContentType("application/json");
                    servletResponse.setCharacterEncoding("UTF-8");
                    servletResponse.getWriter().write(JSON.toJSONString(AjaxResult.error("当前请求存在敏感词汇，请检查后再操作")));
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @SuppressWarnings("rawtypes")
    private Map sensitiveWordMap = null;
    public static int minMatchTYpe = 1;      //最小匹配规则
    public static int maxMatchType = 2;      //最大匹配规则

    /**
     * 构造函数，初始化敏感词库
     */
    @PostConstruct
    public void SensitiveWordFilter() {
        sensitiveWordMap = new SensitiveWordInit().initKeyWord();
    }

    /**
     * 判断文字是否包含敏感字符
     *
     * @param txt       文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     * @version 1.0
     */
    public boolean isContaintSensitiveWord(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = this.CheckSensitiveWord(txt, i, matchType); //判断是否包含敏感字符
            if (matchFlag > 0) {    //大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取文字中的敏感词
     *
     * @param txt       文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return
     */
    public Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<String>();
        /*去除全部空格*/
        //txt = txt.replaceAll(" ","");
        for (int i = 0; i < txt.length(); i++) {
            int length = CheckSensitiveWord(txt, i, matchType);    //判断是否包含敏感字符
            if (length > 0) {    //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;    //减1的原因，是因为for会自增
            }
        }

        return sensitiveWordList;
    }

    /**
     * 替换敏感字字符
     *
     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     * @version 1.0
     */
    public String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);     //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 获取替换字符串
     *
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    @SuppressWarnings({"rawtypes"})
    public int CheckSensitiveWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        /*标识，这是这次关键词判断的第几次*/
        int spaceCount = 0;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++, spaceCount++) {
            word = txt.charAt(i);
            /*判断空格,并且不是第一个敏感词的开始词*/
            if (spaceCount != 0 && Character.isSpaceChar(word)) {
                matchFlag++;
                continue;
            }
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if (nowMap != null) {     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if ("1".equals(nowMap.get("isEnd"))) {       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                    if (minMatchTYpe == matchType) {    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            } else {     //不存在，直接返回
                break;
            }
        }
        if (matchFlag < 2 || !flag) {        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }
}
