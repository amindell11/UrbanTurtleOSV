# UrbanTurtleOSV

#include <Enes100.h>
Enes100 rfComm("UrbanTurtle",WATER,53,8,9);
long start;
long start2;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  start = millis();
  start2 = millis();
  rfComm.requestLocation();
}
int state2;
void loop() {
  state2 = rfComm.updateLocRequest();
  if(state2>=4){
    Serial.println(millis()-start);
    start = millis();
    rfComm.requestLocation();
    delay(100);
  }
  //Serial.println(state2);
  //Serial.println(millis()-start2);
  start2 = millis();
}
