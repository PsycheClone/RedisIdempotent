#!/bin/bash
mkdir -p copyFromOne/folder1
mkdir -p copyFromOne/folder2
mkdir -p copyFromTwo/folder1
mkdir -p copyFromTwo/folder2
mkdir copyToOne
mkdir copyToTwo
for i in {1..500}
do
   touch copyFromOne/folder1/a$i.txt
   touch copyFromOne/folder2/a$i.txt
   touch copyFromTwo/folder1/b$i.txt
   touch copyFromTwo/folder2/b$i.txt
done