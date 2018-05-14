##Problem: Connection interruption will break idempotent file route

###Prerequisites
Start redis container.  
``docker run -p 6379:6379 -d redis:4.0``

###Test
- Execute createFiles.sh  
This will create the folder structure with 500 files in each folder.
- Start spring boot app
- Simulate connection failure.  I block port 6379 for 4 seconds.  
```
./blockPortMac.sh
or
./blockPortLinux.sh
```
- Exception:
```
org.springframework.data.redis.RedisConnectionFailureException: java.net.SocketTimeoutException: Read timed out; nested exception is redis.clients.jedis.exceptions.JedisConnectionException: java.net.SocketTimeoutException: Read timed out
```
- Then add new files to the folders with:  
`./addMoreFiles.sh`
- The script has created 4 more files, 1 in each folder.  
At this point, files are being copied that should not be copied anymore because they are member of the key `key1`. And it keeps copying in an endless loop.