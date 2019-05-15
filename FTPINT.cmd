
CLS

ECHO.
ECHO Compile and Run the FtpIntegrationNotification to transfer a file (from Port 22 to the local filesystem).  
 FINDSTR "MSG_TO" agent.properties
ECHO.
JAVAC SmtpRelay.java

JAVAC FtpIntegrationNotification.java

IF EXIST FtpIntegrationNotification.class Java FtpIntegrationNotification
