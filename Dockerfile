FROM gradle

ADD ./ .

USER root

RUN gradle -info build -Pprofile=dev
