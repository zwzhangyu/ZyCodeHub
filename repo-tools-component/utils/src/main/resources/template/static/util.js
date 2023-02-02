/**
 * 判断对象是否不为undefined且不为null
 *
 * @param obj
 *            对象
 * @returns obj==null/undefined,return false,other return true
 */
function isNotNull(obj) {
    if (obj == undefined || obj == null)
        return false;
    return true;
}

/**
 * 判断对象是否为空字符串
 *
 * @param obj
 *            字符串对象
 * @returns 对象不为空字符串：true,其他:false
 */
function isNotBlank(obj) {
    if (!isNotNull(obj))
        return false;
    if ($.trim(obj) == '')
        return false;
    return true;
}