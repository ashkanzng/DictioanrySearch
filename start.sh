#!/bin/bash


java -jar DictioanrySearch.jar 0 500      >> 0_500.log   &
java -jar DictioanrySearch.jar 500 1000   >> 500_1000.log  &
java -jar DictioanrySearch.jar 1000 1500  >> 1000_1500.log &
java -jar DictioanrySearch.jar 1500 2000  >> 1500_2000.log &
java -jar DictioanrySearch.jar 2000 3000  >> 2000_2500.log &






