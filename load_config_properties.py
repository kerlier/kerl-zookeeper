#!/usr/bin/python3
# -*- coding: utf-8 -*-
# @Time    : 2020/5/22 下午8:45
# @Author  : kerl
# @Des     : 
# @File    : load_config_properties.py
# @Software: PyCharm
from kazoo.client import KazooClient
import base64

class Properties:
  fileName = ''

  def __init__(self, fileName):
    self.fileName = fileName

  def getProperties(self):
    try:
      pro_file = open(self.fileName, 'r')
      properties = {}
      for line in pro_file:
          if line.find('=') > 0:
            strs = line.replace('\n', '').split('=')
            properties[strs[0]] = strs[1]
    except Exception as e:
      raise e
    else:
        pro_file.close()

    return properties


if __name__ == '__main__':
    path = 'application.properties'

    p = Properties(path)
    zk = KazooClient(hosts='localhost:2181')

    zk.start()

    value = zk.get('/locationHost')

    print(value)
    print(type(value))

    print(type(value[0]))
    print(value[0])

    data1 = value[0].decode()
    print(data1)

    # zk.set('/locationHost', 'str'.encode())
    # properties = p.getProperties()

    # for property in properties:
    #     print(property)
    #     print(properties[property])