FROM myal2023:latest as stgconfigure
COPY . /librdkafka
WORKDIR /librdkafka
RUN ./configure --prefix=/mylib --enable-ssl --enable-gssapi

FROM stgconfigure as stgcompile
RUN make && make install

FROM stgcompile as stgzip
WORKDIR /mylib
RUN cp \
    /usr/lib64/libcrypto.so* \
    /usr/lib64/libcrypt.so* \
    /usr/lib64/libssl.so* \
    /usr/lib64/libsasl2.so* \
    ./lib/
RUN cp -r \
    /usr/lib64/pkgconfig \
    /usr/lib64/sasl2 \
    ./lib/
RUN zip -r mylib.zip ./lib/*.so* ./lib/pkgconfig ./lib/sasl2 ./include ./share
