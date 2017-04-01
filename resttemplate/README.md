#RestTemplate sample and configuration
## SSL and Redirect Strategy
1. This configuration is adaptable with both http and https.
2. Under condition of Post Redirect Get(PRG) mode and body/form-data will be lost during redirect, <em>307</em> is strongly recommended. e.g. nginx http to https config:
```conf
return 307 https://$host$request_uri
```

## Request methods and params
1. getObject() performs to retrieve an object via get method, request entity(HttpEntity) is made up of params(MultivalueMap type) and headers(HttpHeaders).

2. postObject() performs to retrieve a list of objects via post method, note the list type is defined in ParameterizedTypeReference<T>. A header(oauth2 maybe) is appended to http headers.

### Important notes:
As to post or put method, if server @RequestBody, MultivalueMap(String, Object) data is created and send with headers in HttpEntity(data, headers), if server @ReqeustParam, MultivalueMap(String, String) data is created and send with UriComponentsBuilder.requestParams(data)

## hashCode()
Inner class Apis.UserModel illustrates a simple sample on overriding method hasCode() to ensure object equal() method.
