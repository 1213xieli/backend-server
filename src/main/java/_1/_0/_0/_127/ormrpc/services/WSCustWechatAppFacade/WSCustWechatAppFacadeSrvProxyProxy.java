package _1._0._0._127.ormrpc.services.WSCustWechatAppFacade;

public class WSCustWechatAppFacadeSrvProxyProxy implements _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxy {
  private String _endpoint = null;
  private _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxy wSCustWechatAppFacadeSrvProxy = null;
  
  public WSCustWechatAppFacadeSrvProxyProxy() {
    _initWSCustWechatAppFacadeSrvProxyProxy();
  }
  
  public WSCustWechatAppFacadeSrvProxyProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSCustWechatAppFacadeSrvProxyProxy();
  }
  
  private void _initWSCustWechatAppFacadeSrvProxyProxy() {
    try {
      wSCustWechatAppFacadeSrvProxy = (new _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxyServiceLocator()).getWSCustWechatAppFacade();
      if (wSCustWechatAppFacadeSrvProxy != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSCustWechatAppFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSCustWechatAppFacadeSrvProxy)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSCustWechatAppFacadeSrvProxy != null)
      ((javax.xml.rpc.Stub)wSCustWechatAppFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public _1._0._0._127.ormrpc.services.WSCustWechatAppFacade.WSCustWechatAppFacadeSrvProxy getWSCustWechatAppFacadeSrvProxy() {
    if (wSCustWechatAppFacadeSrvProxy == null)
      _initWSCustWechatAppFacadeSrvProxyProxy();
    return wSCustWechatAppFacadeSrvProxy;
  }
  
  public java.lang.String getData(java.lang.String bizType, java.lang.String jsonType) throws java.rmi.RemoteException, custwechatappfacade.client.WSInvokeException{
    if (wSCustWechatAppFacadeSrvProxy == null)
      _initWSCustWechatAppFacadeSrvProxyProxy();
    return wSCustWechatAppFacadeSrvProxy.getData(bizType, jsonType);
  }
  
  public java.lang.String bybHandler(java.lang.String methodName, java.lang.String jsonData) throws java.rmi.RemoteException, custwechatappfacade.client.WSInvokeException{
    if (wSCustWechatAppFacadeSrvProxy == null)
      _initWSCustWechatAppFacadeSrvProxyProxy();
    return wSCustWechatAppFacadeSrvProxy.bybHandler(methodName, jsonData);
  }
  
  public java.lang.String importData(java.lang.String bizType, java.lang.String jsonTpye) throws java.rmi.RemoteException, custwechatappfacade.client.WSInvokeException{
    if (wSCustWechatAppFacadeSrvProxy == null)
      _initWSCustWechatAppFacadeSrvProxyProxy();
    return wSCustWechatAppFacadeSrvProxy.importData(bizType, jsonTpye);
  }
  
  public void updateToken() throws java.rmi.RemoteException, custwechatappfacade.client.WSInvokeException{
    if (wSCustWechatAppFacadeSrvProxy == null)
      _initWSCustWechatAppFacadeSrvProxyProxy();
    wSCustWechatAppFacadeSrvProxy.updateToken();
  }
  
  
}