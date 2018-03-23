typedef union
{
  float number;
  uint8_t bytes[4];
} cfloat_t;

bool newPos = false;

bool getLeftProx() {
  return false;
}
bool getRightProx() {
  return true;
}
bool newUpdate() {
  bool val = newPos;
  return val;
  newPos = false;
  return val;
}

byte getFlags() {
  byte flags = 0;
  if (newUpdate())bitSet(flags, 0);
  if (getLeftProx())bitSet(flags, 1);
  if (getRightProx())bitSet(flags, 2);
  return flags;
}

//GYRO
void writeGyroRate(SoftwareSerial *piComm) {
  cfloat_t rate;
  rate.number = 224.61;
  piComm->write(rate.bytes, 4);
}
//ENC
byte getLeftEnc() {
  return 122;
}
byte getRightEnc() {
  return 41;
}
//POS
void writeX(SoftwareSerial *piComm) {
  cfloat_t x;
  x.number = 301.45;
  piComm->write(x.bytes, 4);
}
void writeY(SoftwareSerial *piComm) {
  cfloat_t y;
  y.number = 102.20;
  piComm->write(y.bytes, 4);
}
void writeTheta(SoftwareSerial *piComm) {
  cfloat_t theta;
  theta.number = 12.53;
  piComm->write(theta.bytes, 4);
}
void sendPackage(SoftwareSerial *piComm) {
  piComm->write(getFlags());
  writeGyroRate(piComm);
  piComm->write(getLeftEnc());
  piComm->write(getRightEnc());
  writeX(piComm);
  writeY(piComm);
  writeTheta(piComm);
}

