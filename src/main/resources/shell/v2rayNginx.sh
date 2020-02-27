#/bin/bash

service v2ray restart

nginx -t
nginx - reload