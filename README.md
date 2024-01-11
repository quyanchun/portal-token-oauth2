yml文件配置

```yaml
# portal-token
portal-token:
    # oauth2
    oauth2:
        server_host:  #server端地址 必填
        client_id:  #申请的client_id 必填
        client_secret:  #申请的client_secret 必填
```

### 1.1、Access-Token

```java
//登录 根据授权码获取Access-Token  方式一
Oauth2TokenUtil.token(code);

//登录 根据账号密码获取Access-Token 方式二
Oauth2TokenUtil.tokenByPassword(username,password)

//刷新 Access-Token
Oauth2TokenUtil.refresh(refreshToken);

//获取用户信息
Oauth2TokenUtil.userinfo(accessToken);

//登出
Oauth2TokenUtil.logout(accessToken);

```

### 1.2、client-Token

```java
// 获取应用的 Client-Token
ClientTokenUtil.clientToken();

// 获取应用的部门信息
ClientTokenUtil.deptInfo(clientToken, PortalDeptType.list);

// 获取应用的用户列表
ClientTokenUtil.userList(clientToken,deptId,nickName,userName,phonenumber,1,10);

// 重置密码
ClientTokenUtil.resetPassword(clientToken,userId,password);

```
