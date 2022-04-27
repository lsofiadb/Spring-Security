## Basic Auth :beginner:
Basically the client of our web application sends a request to the server, if the user does not send the username and password inside 
request headers, the server returns 401 Unauthorized.
With this method it's necessary to send the username and password (they are encoded through Basic 64) inside the request headers, 
for every single request, for have access to the resources. 

This method is used to access to external APIs. But it's not useful with  modern web applications. 
Also, you can't log out.

