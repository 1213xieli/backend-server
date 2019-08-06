/**
 * WSWSAppDataDealFacadeSrvProxy.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zb.byb.webService.client;

import com.zb.byb.webService.WSInvokeException;

public interface WSWSAppDataDealFacadeSrvProxy extends java.rmi.Remote {
    public void push(java.lang.String bizType) throws java.rmi.RemoteException, WSInvokeException;

    public java.lang.String getData(java.lang.String bizType, java.lang.String jsonStr) throws java.rmi.RemoteException, WSInvokeException;

    public java.lang.String login(java.lang.String jsonStr) throws java.rmi.RemoteException, WSInvokeException;

    public java.lang.String importData(java.lang.String bizType, java.lang.String jsonStr) throws java.rmi.RemoteException, WSInvokeException;

    public java.lang.String updateData(java.lang.String bizType, java.lang.String jsonStr) throws java.rmi.RemoteException, WSInvokeException;

    public byte[] download(java.lang.String jsonStr) throws java.rmi.RemoteException, WSInvokeException;
}
