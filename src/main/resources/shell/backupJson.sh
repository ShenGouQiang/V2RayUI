#/bin/bash

nowDate=$(date -d today +%Y%m%d_%H%M%S)

cp /etc/v2ray/config.json /root/v2ray/sources/backJsonDirectory/config$nowDate