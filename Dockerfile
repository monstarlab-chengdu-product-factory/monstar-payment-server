FROM gradle

ADD ./ .

USER root

RUN gradle -info clean

RUN gradle -info build -Pprofile=dev