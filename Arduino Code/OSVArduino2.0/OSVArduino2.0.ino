#include <SoftwareSerial.h>
SoftwareSerial piComm(10, 11);

void setup() {
  Serial.begin(9600);
  piComm.begin(9600);
  Serial.print(getFlags(), BIN);
}
long start;
void loop() {
  sendPackage(&piComm);
  delay(10);
  Serial.println(millis()-start);
  start = millis();
}
