import re

f = open('characters.txt', 'r').read()
lines = f.split('\n')
t = 0
lc = 0
for l in range(len(lines)):
    # logical xor
    out = re.sub('[ ]{2,}', '\t', lines[l])
    lines[l] = out
    if (lines[l].find('|') != -1 ^ lines[l].find('\t') != -1):
        if (lines[l].find('|') != -1):
            chunks = lines[l].split('|')
            if (len(chunks) != 3): print('bad line @ ln:%s; invalid number of deliminators' % str(l+1))
            else:
                if (chunks[2].isnumeric()):
                    t += int(chunks[2])
                    lc += 1
                else: print('bad line @ ln:%s; invalid number' % str(l+1))
        elif (lines[l].find('\t') != -1):
            chunks = lines[l].split('\t')
            if (len(chunks) != 2): print('bad line @ ln:%s; invalid number of deliminators' % str(l+1))
            else:
                if (chunks[0][0].isnumeric()):
                    t += int(chunks[0][0])
                    lc += 1
                else: print('bad line @ ln:%s; invalid number' % str(l+1))
    else: print('bad line @ ln:%s; no deliminators or conflicting deliminators' % str(l+1))
print('the total years in the cast are %i avg %f' % (t, t/lc))