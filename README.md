# Sample mutual tls implementation

##### This is my attempt at creating a sample spring boot application with mutual tls 
##### enabled, also how to create keystore and truststore.

So lets get started.
First step would be to create keystore and truststore, execute the below steps in command prompt
1. Create a keystore. <br>
   keytool -genkeypair -alias mtls-sample -dname cn=localhost -validity 365 -keyalg RSA -keysize 2048 -keystore mtls-keystore.jks
2. Export the public key to certificate. <br>
   keytool -exportcert -rfc -keystore mtls-keystore.jks -alias mtls-sample > mtls-cert.pem
3. Import the certificate to truststore. <br>
   keytool -keystore mtls-truststore.jks -storepass storepass123 -importcert -alias mtls-sample -file mtls-cert.pem

I am not going into details of commands and what they do and how ssl works, you can check [this](http://www.steves-internet-guide.com/ssl-certificates-explained/) or [this](https://www.digitalocean.com/community/tutorials/java-keytool-essentials-working-with-java-keystores) 
for some good explanation. 


Once you have generated the keystore and truststore copy them to the resources folder of each module.
Update the application.yml file with correct alias, password and key/truststore name if you changed them in the above commands.

Start the 3 application and test by invoking https://localhost:8443/hello and https://localhost:9443/hello 

To watch ssl handshake in action pass the following vm argument while starting -Djavax.net.debug=all or -Djavax.net.debug=ssl:record or -Djavax.net.debug=ssl:handshake