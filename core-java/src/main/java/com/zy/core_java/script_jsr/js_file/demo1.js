var DB = Java.type("cn.hutool.db.Db")
print(DB.use().findAll("sys_user"))