#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>

#define OSC_IN 12
#define OSC_OUT 13

int x = 0;

const char* ssid = "LCC3-1_Andar";
const char* password = "GuestLCC3";

ESP8266WebServer server(8080);

const int led = 2; // ESP-12

void handleActive() {
  x = 1;
  server.send(200, "text/plain", "ativado");
}

void handleDesactive() {
  x = 0;
  server.send(200, "text/plain", "desativado");
}

void handleRoot() {
  digitalWrite(led, 1);
  server.send(200, "text/plain", "hello from esp8266!");
  digitalWrite(led, 0);
}

void handleNotFound() {
  digitalWrite(led, 1);
  String message = "File Not Found\n\n";
  message += "URI: ";
  message += server.uri();
  message += "\nMethod: ";
  message += (server.method() == HTTP_GET) ? "GET" : "POST";
  message += "\nArguments: ";
  message += server.args();
  message += "\n";
  for (uint8_t i = 0; i < server.args(); i++) {
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(404, "text/plain", message);
  digitalWrite(led, 0);
}

uint8_t mao() {
  register uint8_t now=0, prev=1, value=0;
  for(register uint8_t i=0; i<200; i++) {
     now = GPIP(OSC_IN);
     value += now ^ prev;
     prev = now;
     if( now ) GPOC = (1 << OSC_OUT);
     else      GPOS = (1 << OSC_OUT);
  }
  return value;
}

void setup(void) {
  pinMode(OSC_IN, INPUT);
  pinMode(OSC_OUT, OUTPUT);
  byte mac[6];

  pinMode(led, OUTPUT);
  digitalWrite(led, 0);
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.macAddress(mac);
  Serial.println("");

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    WiFi.begin(ssid, password);
    delay(10000+(mac[5]<<6)); // delay between 10s and 26s depending on MAC

  }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  if (MDNS.begin("esp8266")) {
    Serial.println("MDNS responder started");
  }

  server.on("/", handleRoot);
  server.on("/active", handleActive);
  server.on("/desactive", handleDesactive);

  server.on("/inline", []() {
    server.send(200, "text/plain", "this works as well");
  });

  server.onNotFound(handleNotFound);

  server.begin();
  Serial.println("HTTP server started");
}

void loop(void) {
  server.handleClient();
  if(mao() >= 130 || x) {
    digitalWrite(OSC_OUT, LOW);
  }
  else {
    digitalWrite(OSC_OUT, HIGH);
  }
}
