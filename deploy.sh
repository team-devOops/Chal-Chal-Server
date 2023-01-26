#/bin/bash

function check_df() {
  git fetch
  master=$(git rev-parse main)
  remote=$(git rev-parse origin/main)

  echo "-----------"
  echo $master
  echo $remote

  if [[ $master == $remote ]]; then
    echo -e "${txtred} [$(date)] 이미 최신 버전으로 배포할 수 없습니다.! 😫"
    exit 0
  fi

  git pull origin main
}


NAME=chalchal

### 최신 브랜치 체크
check_df

docker build --tag chalcha:lastest .

RUNNER=$(docker ps -f name=${NAME} | grep -w ${NAME} | cut -d " " -f1)
echo ${RUNNER}

if [ ! -z ${RUNNER} ];
then
  docker rm ${RUNNER}
fi

docker run -p 8080:8080 -d --name chalchal chalcha:lastest
