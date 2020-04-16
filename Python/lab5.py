import re
line = "28TO THE HESITATING PURCHASER33If sailor tales to sailor tunes,36Storm and adventure, heat and cold,36If schooners, islands, and maroons,33And buccaneers, and buried gold,32And all the old romance, retold28Exactly in the ancient way."
print(re.sub(r"\d+", "\n", line))
