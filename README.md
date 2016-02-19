# cas-openid-client
OpenID client to test Jasig CAS openid implementation

This application was built to test Jasig CAS OpenId implementatio, specifically the Stateless mode.
To test it:

#build it
maven clean package
#start the client
java -jar openid-client-1.0-SNAPSHOT.jar
#open browser and access the service
https://localhost:9000/openid/auth

If you want to test stateless.mode (no association betwen RP and OP) change the value "openid-associate" 
in config.properties and rerun the test

NOTE: As per the last version available of CAS, stateless mode needs a change in configuration of the server.
In your "cas.properties" change the values of the property "st.numberOfUses" to "2".
