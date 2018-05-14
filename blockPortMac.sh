cd /etc
sudo mv pf.conf temppf.conf
sudo mv pftemp.conf pf.conf
sudo pfctl -f /etc/pf.conf
sudo pfctl -e
sleep 4
sudo mv pf.conf pftemp.conf
sudo mv temppf.conf pf.conf
sudo pfctl -f /etc/pf.conf
sudo pfctl -e