FROM java:8
MAINTAINER George <ubuntu_vip@sina.cn>
VOLUME /tmp
ADD eshop-product-service.jar eshop-product-service.jar
#RUN bash -c 'touch /eshop-product-service.jar'
EXPOSE 8768
ENTRYPOINT ["java","-Xmx128m","-Djava.security.egd=file:/dev/./urandom","-jar","/eshop-product-service.jar"]