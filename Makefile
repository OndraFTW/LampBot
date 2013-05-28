
JC=javac
CFLAGS=
ARGS=1 1 1 1 1 1 1 1
CLASS_FILES=KingdomState.class Information.class Mood.class Limits.class Attack.class Defense.class Main.class
TEAM_NAME=LampBot

.PHONY: %.class, clean

main: $(CLASS_FILES)
	jar cfm $(TEAM_NAME).jar src/MANIFEST -C . .
	cp src/$(TEAM_NAME) ./$(TEAM_NAME)
	cat ./$(TEAM_NAME).jar >> ./$(TEAM_NAME)
	chmod +x $(TEAM_NAME)

%.class: src/%.java
	$(JC) $(CFLAGS) $^

clean:
	rm -r ./strategy
	rm $(TEAM_NAME).jar

