package com.example.wideking.arhitecturetest.api.dataModels;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ResponseData {

    public boolean error = false;
    public String errorDescription;
    public String response;
    public Document responseObject;
    public Object customObject;
    public String controllerNotification;
    public String notification;
    public String type;
    public String message;
    private String URL;

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseData{");
        sb.append("error=").append(error);
        sb.append(", errorDescription='").append(errorDescription).append('\'');
        sb.append(", response='").append(response).append('\'');
        sb.append(", responseObject=").append(responseObject);
        sb.append(", customObject=").append(customObject);
        sb.append(", controllerNotification='").append(controllerNotification).append('\'');
        sb.append(", notification='").append(notification).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Check if request has error
     *
     * @return boolean true if has error or false if not
     */
    public boolean hasError() {
        return error;
    }

    /**
     * Set error response and error description
     *
     * @param _error            boolean if has error
     * @param _errorDescription String error description
     */
    public void setError(boolean _error, String _errorDescription) {
        error = _error;
        errorDescription = _errorDescription;
    }

    /**
     * Set string response from server
     *
     * @param _response - String server response
     */
    public void setResponse(String _response) {
        response = _response;
    }

    /**
     * Get raw server response
     *
     * @return String server response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Get custom object from request that was sent
     * from caller of http request
     *
     * @return Object custom object
     */
    public Object getCustomObject() {
        return customObject;
    }

    /**
     * Sets the custom object that is sent through http method
     *
     * @param _customObject - Object
     */
    public void setCustomObject(Object _customObject) {
        customObject = _customObject;
    }

    /**
     * Parse string response to JSON object
     *
     * @return - JSONObject|null
     */
    public Document getResponseObject() {

        if (getResponse() == null) {
            setError(true, "Error parsing response, Missing response data or no response");
            return null;
        }

        if (getResponse().equalsIgnoreCase("")) {
            setError(true, "Error parsing response, Missing response data or no response");
            return null;
        }

        if (responseObject != null) {
            return responseObject;
        }

        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(new StringReader(getResponse())));
            responseObject = doc;
            return doc;

        } catch (ParserConfigurationException e) {
            setError(true, "Parsing configuration error");
            //e.printStackTrace();
            return null;
        } catch (SAXException e) {
            setError(true, "Error parsing response, Invalid XML ");
            //e.printStackTrace();
            return null;
        } catch (IOException e) {
            setError(true, "Error parsing response");
            //e.printStackTrace();
            return null;
        }
    }


    public String getControllerNotification() {
        return controllerNotification;
    }

    public void setControllerNotification(String controllerNotification) {
        this.controllerNotification = controllerNotification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getErrorDescription() {

        return errorDescription;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }


}