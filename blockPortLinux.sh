iptables -I FORWARD -p tcp --destination-port 6379 -j DROP
iptables -L -v -n --line-numbers|grep 6379
sleep 4
iptables -L -v -n --line-numbers|grep 6379
iptables -D FORWARD 1
iptables -L -v -n --line-numbers|grep 6379