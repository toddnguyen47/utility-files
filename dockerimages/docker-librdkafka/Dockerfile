FROM myal2023:latest as build1
COPY . /librdkafka
WORKDIR /librdkafka
RUN ./configure --prefix=/mylib && make && make install
 
FROM build1 as build2
WORKDIR /mylib
RUN zip -r mylib.zip lib/*.so* include
