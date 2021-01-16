#!/usr/bin/env bash
#bash!

allapps="ssc-website"
DEFAULT_DOCKER_REG="172.18.45.237:5000"
function usage() {
  echo "Usage: $0 [-r <dockerRegistryIp:port>] [microService name]"
}

while getopts 'r:' OPT; do
  case $OPT in
    r)
      DOCKER_REG="$OPTARG";;
    ?)
      usage
      exit 1
  esac
done

shift $(($OPTIND - 1))

if [ -z $1 ]; then
  apps=$allapps
else
  apps=$1
fi

if [ -n "$DOCKER_REG" ]; then
  MVN_OPT="-Ddocker.image.prefix=$DOCKER_REG"
else
  MVN_OPT="-Ddocker.image.prefix=$DEFAULT_DOCKER_REG"
fi

echo ${apps}

mvn clean install -am
if [[ $? -ne 0 ]]; then
    echo "mvn clean install fail ！！！"
	exit 1
else
	echo "mvn clean install success ."
fi

apps=${apps//,/ }
for app in $apps
do
	echo =====================================================================================
    	echo build docker image [$app]
 	mvn $MVN_OPT docker:build docker:push
 	if [[ $? -ne 0 ]]; then
        echo "build docker fail ！！！"
	    exit 1
    else
	    echo "build docker success ."
    fi
done

sleep 5s
mvn clean
sleep 5s

del_docker_imgs=$(docker images -f dangling=true -q)
for del_docker_img in $del_docker_imgs
do
    docker rmi -f $del_docker_img
done
echo "delete useless docker images finish."
