

## 安装

```shell
[root@ixfosa ~]# cd /opt/
[root@ixfosa opt]# rz
[root@ixfosa opt]# ll
total 33572
-rw-r--r--. 1 root root 18850824 Jun 15 17:03 erlang-21.3-1.el7.x86_64.rpm
-rw-r--r--. 1 root root 15520399 Jun 15 17:03 rabbitmq-server-3.8.8-1.el7.noarch.rpm

# 查看Linux版本
[root@ixfosa opt]# uname -a
Linux ixfosa 3.10.0-693.el7.x86_64 #1 SMP Tue Aug 22 21:09:27 UTC 2017 x86_64 x86_64 x86_64 GNU/Linux


YDNJFWQXEZTKLEOTPAXQ
tsd6yE006lstNqk1oeW3Q==


[root@ixfosa opt]# /sbin/service rabbitmq-server start 
Redirecting to /bin/systemctl start rabbitmq-server.service
Job for rabbitmq-server.service failed because the control process exited with error code. See "systemctl status rabbitmq-server.service" and "journalctl -xe" for details.
[root@ixfosa opt]# journalctl -xe
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: ===========
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: attempted to contact: [rabbit@ixfosa]
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: rabbit@ixfosa:
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: * unable to connect to epmd (port 4369) on ixfosa: timeout (timed out)
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: Current node details:
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: * node name: 'rabbitmqcli-14424-rabbit@ixfosa'
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: * effective user's home directory: /var/lib/rabbitmq
Oct 12 15:26:32 ixfosa rabbitmqctl[14424]: * Erlang cookie hash: PlTEYbxgLM1gNWIAP/ES5A==
Oct 12 15:26:32 ixfosa systemd[1]: Failed to start RabbitMQ broker.
-- Subject: Unit rabbitmq-server.service has failed
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit rabbitmq-server.service has failed.
-- 
-- The result is failed.
Oct 12 15:26:32 ixfosa systemd[1]: Unit rabbitmq-server.service entered failed state.
Oct 12 15:26:32 ixfosa systemd[1]: rabbitmq-server.service failed.
Oct 12 15:26:32 ixfosa polkitd[572]: Unregistered Authentication Agent for unix-process:14241:151327 (system bus name :1.30, object path /o
Oct 12 15:26:39 ixfosa dhclient[856]: DHCPREQUEST on ens33 to 192.168.1.254 port 67 (xid=0x25b1f2af)
Oct 12 15:26:39 ixfosa dhclient[856]: DHCPACK from 192.168.1.254 (xid=0x25b1f2af)
Oct 12 15:26:41 ixfosa dhclient[856]: bound to 192.168.1.129 -- renewal in 838 seconds.
Oct 12 15:26:42 ixfosa systemd[1]: rabbitmq-server.service holdoff time over, scheduling restart.
Oct 12 15:26:43 ixfosa systemd[1]: Starting RabbitMQ broker...
-- Subject: Unit rabbitmq-server.service has begun start-up
-- Defined-By: systemd
-- Support: http://lists.freedesktop.org/mailman/listinfo/systemd-devel
-- 
-- Unit rabbitmq-server.service has begun starting up.
Oct 12 15:26:43 ixfosa rabbitmq-server[14486]: Configuring logger redirection
[root@ixfosa opt]#  vi /etc/hosts
 
```

