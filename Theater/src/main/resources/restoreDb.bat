C:
cd %1
mysql -h %2 --user=%3 --password=%4 -e "create database %5"
mysql -h %2 --user=%3 --password=%4  %5<%6
EXIT 0