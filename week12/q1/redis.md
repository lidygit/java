# Redis

## 主从复制

```
redis-server redis6379.conf
redis-server redis6380.conf

redis-cli -p 6379

redis-cli -p 6380
slaveof 127.0.0.1 6379
```



## sentinel高可用 主从切换

```
redis-server sentinel0.conf --sentinel
redis-server sentinel1.conf --sentinel
```



## Cluster集群 分片

```
redis-cli --cluster create 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384

redis-cli -c -p 6382
```