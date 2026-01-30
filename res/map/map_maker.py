# J'ai la giga flemme de la faire à la main

maps = []

# Blank map creation
height, width = 350, 150
for i in range(width):
    maps.append([])
    for j in range(height):
        maps[-1].append(0)

# Display the map in the console
def to_string(sep = " ") -> str:
    result = ""
    for i in range(len(maps[0])):
        for j in range(len(maps)):
            current = str(maps[j][i])
            if current == "0": current = "  "
            while len(current) < 2: current = "0" + current
            result += current + sep
        result = result[:-1] + "\n"
    return result

# Create a building in the map
def building(x, y, width, height, block):
    for i in range(width): maps[x + i][y] = block
    for i in range(width): maps[x + i][y + height - 1] = block
    for i in range(height): maps[x][y + i] = block
    for i in range(height): maps[x + width][y + i] = block

# Fill a rectangle of the map
def rectangle(x, y, width, height, block):
    for i in range(width):
        for j in range(height):
            maps[x + i][y + j] = block

# Main program

# Spawn
building(2, 30, 10, 16, 30)

# MM
building(60, 10, 35, 15, 30)

# EB
building(115, 70, 25, 80, 30)

# JJ
building(10, 70, 30, 80, 30)

# BU
building(10, 160, 30, 60, 30)

# EL
building(115, 160, 25, 60, 30)

# Admin
building(55, 155, 45, 30, 30)

# RU
building(55, 230, 45, 40, 30)

# PA
building(55, 300, 45, 30, 30)

# Ways
# Spawn - EB
rectangle(12, 36, 96, 4, 10)
# All left
rectangle(48, 39, 4, 291, 10)
# All right
rectangle(104, 39, 4, 291, 10)
# JJ - EB Top
rectangle(48, 70, 56, 4, 10)
# JJ - EB Middle
rectangle(41, 110, 74, 4, 10)
# JJ - EB Bottom
rectangle(48, 146, 56, 4, 10)
# EL - BU Top
rectangle(41, 190, 74, 4, 10)
# EL - BU Bottom
rectangle(48, 222, 56, 4, 10)
# Admin In
rectangle(76, 226, 4, 4, 10)
# MM In Top
rectangle(76, 25, 4, 12, 10)
# PA Middle
rectangle(48, 278, 56, 4, 10)
# PA In
rectangle(76, 282, 4, 18, 10)
# RU In Top
rectangle(76, 150, 4, 6, 10)
# RU In Bottom
rectangle(76, 150, 4, 6, 10)
# Spawn - Admin
rectangle(76, 185, 4, 6, 10)

# Create the file
try:
    file = open("res/Map/map.txt", "w")
    file.write(to_string())
    file.close()
except:
    file = open("map.txt", "w")
    file.write(to_string())
    file.close()