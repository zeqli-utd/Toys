#!/usr/bin/python
# Author: Zeqing Li

import urllib.request
import urllib.parse
import os
from html.parser import HTMLParser
from bs4 import BeautifulSoup
import re

# 1. Download syllabus.html
# Download the file from `url` and save it locally under `filepath`:

url = "http://www3.cs.stonybrook.edu/~porter/courses/cse306/s16/syllabus.html"
remote = "http://www3.cs.stonybrook.edu/~porter/courses/cse306/s16/"
local_filename, headers = urllib.request.urlretrieve(url)
html = open(local_filename)
soup = BeautifulSoup(html, 'html.parser')
dl_list = []

for link in soup.find_all('a'):
    if(isinstance(link.get('href'), str) and re.match('(slides|notes)/(.+\.pdf)', link.get('href'))):
        dl_list.append(link.get('href'))
print(dl_list)

currentdir = os.path.dirname(os.path.abspath(__file__));

for i in range(0, len(dl_list)):
    dlpath = remote + dl_list[i]
    filename = re.search('(slides|notes)/(.+\.pdf)', dlpath).group(2)
    filepath = currentdir + "\\" + filename
    print("Downloading file: " + filename)
    # print("File Path: " + filepath)
    # print("Download file from path: "+ dlpath)
    # print("File save as: " + filename)
    # urllib.request.urlretrieve(dlpath, filepath)
