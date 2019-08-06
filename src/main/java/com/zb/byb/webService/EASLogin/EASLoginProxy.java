/**
 * EASLoginProxy.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zb.byb.webService.EASLogin;

import com.zb.byb.webService.WSInvokeException;

public interface EASLoginProxy extends java.rmi.Remote {
    public com.zb.byb.webService.client.WSContext login(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType, java.lang.String authPattern) throws java.rmi.RemoteException;

    public com.zb.byb.webService.client.WSContext login(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType, java.lang.String authPattern, int isEncodePwd) throws java.rmi.RemoteException;

    public com.zb.byb.webService.client.WSContext login(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType) throws java.rmi.RemoteException;

    public boolean logout(java.lang.String userName, java.lang.String slnName, java.lang.String dcName, java.lang.String language) throws java.rmi.RemoteException;

    public com.zb.byb.webService.client.WSContext loginSale(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType) throws java.rmi.RemoteException;

    public java.lang.String loginMaterialQR(java.lang.String jsonStr) throws java.rmi.RemoteException;

    public java.lang.String loginBfb(java.lang.String jsonStr) throws java.rmi.RemoteException;

    public java.lang.String loginZBAPP(java.lang.String jsonStr) throws java.rmi.RemoteException, WSInvokeException;
}
