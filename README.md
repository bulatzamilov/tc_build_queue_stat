# TeamCity Build Queue Statistics Plugin

## Why do we need statistics

For the purpose of maitaining optimization in TeamCity it is really needed to establish main metrics that will indicate did we perform right changes to TC. One of key metrics I suggest to use is number of success builds that TC can perform within a day. More builds were successfull - more artifacts we have and therefore we may go further with build lifecycle - perform integration/functional/load testing before packaging and delivery.

Daily successfull builds (DSB) is just one of key metrics. I also suggest to check following:
* Daily failed builds (DFB)
* Derivation from above - daily builds health rate DBHR (%) = 100 * DFB / (DFB + DSB)

For the builds count to use a daily interval is enough to track its amount and health rate. But to determine how TC deals with thousands builds I think we need more detailed data as the velocity of build queue - how fast builds are processed by queue. Faster configuration comes to be built into some agent - faster we got the result about build success/fail. I choosed 5-minutes interval to check metrics because for smaller interval will be generated more data, but bigger interval won't show in details what were happend inside. I think for particular instance of TC you may use any other interval if this one doesn't fit your expectancies.

Build queue related metrics, checking with 5-minutes interval:
* Queue size
* Builds coming into
* Builds processed to agents

## Install

To build plugin:
'''cli
mvn package
''' 

Copy zip file from target/ directory to <TeamCity data dir>/plugins and restart TeamCity.