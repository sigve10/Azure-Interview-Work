#!/bin/bash

cd /home/site/repository/frontend

npm ci
npm run build
cp -r dist/* /home/site/wwwroot