#!/usr/bin/env bash

docker build -t ptviet/payslip .
docker run -p 8088:8088 ptviet/payslip