FROM alpine
ARG default_dir="/opt/files"

# Install pre-reqs
RUN apk update && apk add --no-cache \
  bash \
  build-base \
  curl \
  git \
  linux-headers \
  ncurses-dev \
  openssl \
  readline-dev \
  tzdata \
  unzip \
  wget

# Install lua
RUN mkdir -p $default_dir

# Extract source files and build!
# Install Lua
WORKDIR $default_dir
RUN wget https://www.lua.org/ftp/lua-5.3.5.tar.gz
RUN tar -zxf lua-5.3.5.tar.gz --directory $default_dir
RUN rm -v $default_dir/lua-5.3.5.tar.gz
WORKDIR $default_dir/lua-5.3.5
RUN make linux && make install
WORKDIR $default_dir
RUN rm -rfv $default_dir/lua-5.3.5
# Install luarocks
WORKDIR $default_dir
RUN wget http://luarocks.org/releases/luarocks-3.3.1.tar.gz
RUN tar -zxf luarocks-3.3.1.tar.gz --directory $default_dir
RUN rm $default_dir/luarocks-3.3.1.tar.gz
WORKDIR $default_dir/luarocks-3.3.1
RUN ./configure && make build && make install
WORKDIR /home/
RUN rm -rfv $default_dir/luarocks-3.3.1
RUN git config --global url."https://".insteadOf git://
RUN luarocks install busted
RUN luarocks install lua-periphery

# Add a jenkins-docker user and add it to dialout
RUN adduser -D jenkins-docker && \
  adduser jenkins-docker dialout

# Final command!
CMD ["/bin/bash"]

