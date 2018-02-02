# introduction
This is a RESTful Web Server using Google libphonenumber library to identify phonenumbers. It's Java base project and developed by using Eclipse Java EE. 

# Preparation
Before using this project, you need to:
 - Install [Java](https://www.java.com/en/download/help/download_options.xml)
 - Download [Eclipse Java EE](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2)
 - Download and unzip Tomcat [V9.0.4](https://tomcat.apache.org/download-90.cgi).

# Deploying
1 Download and unzip this package.\
2 Open Eclipse Java EE. Choose a workspace you like, click "launch".\
3 After getting in Eclipse, select "File->Open Project From File System".\
![alt text](https://github.com/KignorChan/libnumberWebServer/blob/master/capture/Screen%20Shot%202018-02-01%20at%209.01.55%20PM.png)
4 Click "Directory", Find the package you just download and unzip, Click "Open", then click "Finish".\
5 In project explorer, right click package name "libnumber", click "Run as -> Run on server".\
![alt text](https://github.com/KignorChan/libnumberWebServer/blob/master/capture/Screen%20Shot%202018-02-01%20at%209.05.36%20PM.png)
6 You need to deploy server. Select "Tomcat V9.0 Server" in the list, click next.\
![alt text](https://github.com/KignorChan/libnumberWebServer/blob/master/capture/Screen%20Shot%202018-02-01%20at%209.06.21%20PM.png)
7 Click browse, select the Tomcat package you download. Click Finish.\
![alt text](https://github.com/KignorChan/libnumberWebServer/blob/master/capture/Screen%20Shot%202018-02-01%20at%209.06.52%20PM.png)
8 The server has started. You can test in chrome browser address by typing:
![alt text](https://github.com/KignorChan/libnumberWebServer/blob/master/capture/Screen%20Shot%202018-02-01%20at%209.11.31%20PM.png)
```
http://localhost:8080/libnumber/api/phonenumbers/parse/text/Seneca%20Phone%20Number%3A%20416-491-5050" 
```

# License
Here is [LICENSE](LICENSE)
