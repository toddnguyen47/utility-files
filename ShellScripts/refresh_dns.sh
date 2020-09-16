#!/bin/bash

sudo /etc/init.d/dns-clean restart
sudo /etc/init.d/networking force-reload
