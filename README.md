# Sample mutual tls implementation

##### This is my attempt at creating a sample spring boot application with mutual tls enabled, also how to create a Keystore and Truststore.
So let's get started. The first step would be to create Keystore and Truststore, execute the below steps in the command prompt
Create a Keystore.
1. keytool -genkeypair -alias mtls-sample -dname cn=localhost -validity 365 -keyalg RSA -keysize 2048 -keystore mtls-keystore.jks
   Export the public key to the certificate.
2. keytool -exportcert -rfc -keystore mtls-keystore.jks -alias mtls-sample > mtls-cert.pem
   Import the certificate to Truststore.
3. keytool -keystore mtls-truststore.jks -storepass storepass123 -importcert -alias mtls-sample -file mtls-cert.pem


I am not going into details of commands and what they do and how SSL works, you can check [this](http://www.steves-internet-guide.com/ssl-certificates-explained/) or [this](https://www.digitalocean.com/community/tutorials/java-keytool-essentials-working-with-java-keystores) for some good explanation.

Once you have generated the Keystore and truststore copy them to the resources folder of each module. 
Update the application.yml file with the correct alias, password, and key/truststore name if you changed them in the above commands.

Start the 3 applications and test by invoking https://localhost:8443/hello and https://localhost:9443/hello

To watch SSL handshake in action pass the following VM argument while starting -Djavax.net.debug=all or -Djavax.net.debug=ssl:record or -Djavax.net.debug=ssl:handshake