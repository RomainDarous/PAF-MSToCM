import matplotlib.pyplot as plt
import csv
import numpy as np
import os
from mpl_toolkits import mplot3d
import matplotlib.colors as colors
import argparse



file = open(os.path.join(os.path.dirname(__file__), "data.csv"))
fichier = csv.reader(file, delimiter=',')
new_color = list(colors.cnames.keys())

def init_cmd():
    ap = argparse.ArgumentParser()
    ap.add_argument("-g", "--groupid", default=1, help="LOL")
    args = vars(ap.parse_args())
    return args


args = init_cmd()

def trajectoireBarycentre(idgroupe):

    X=[]
    Y=[]
    Z=[]
    separationX=[]
    separationY=[]
    separationZ=[]
    fusionX=[]
    fusionY=[]
    fusionZ=[]
    iter=0
    current_col = 10

    res=[]

    ax = plt.axes(projection ='3d')

    for ligne in fichier:
        res.append(ligne)

    for i in range(len(res)):
        ligne = res[i]
        
        if iter ==2:
            if (ligne[0] == str(idgroupe))&(len(ligne) == len(res[1])):
                X.append(float(ligne[1]))
                Y.append(float(ligne[2]))
                Z.append(int(ligne[-1]))
                if int(ligne[7]) > nb:
                    separationX.append(float(ligne[1]))
                    separationY.append(float(ligne[2]))
                    separationZ.append(int(ligne[-1]))

                    plottingOther(ligne, res, new_color, current_col, i, ax)
                    current_col += 1 
                    if current_col == len(new_color):
                        current_col = 10                  

                    nb = int(ligne[7])
                elif int(ligne[7]) < nb:
                    fusionX.append(float(ligne[1]))
                    fusionY.append(float(ligne[2]))
                    fusionZ.append(int(ligne[-1]))


                    plottingOther(ligne, res, new_color, current_col, i, ax)
                    current_col += 1
                    if current_col == len(new_color):
                        current_col = 10


                    nb=int(ligne[7])

        elif iter==1:
            nb=int(ligne[7])
            iter=2

            X.append(float(ligne[1]))
            Y.append(float(ligne[2]))
            Z.append(int(ligne[-1]))


        else:
            iter=1



    ax.plot3D(X, Y, Z, 'green')
    ax.set_xlabel('X')
    ax.set_ylabel('Y')
    ax.set_zlabel('Temps (s)')
    ax.scatter(separationX, separationY, separationZ,label ='Separation', c='r')
    ax.scatter(fusionX, fusionY, fusionZ,label ='fusion', c='b')
    ax.legend()
    ax.set_title('Trajectoire du groupe '+ str(idgroupe))

    plt.show()



def plottingOther(ligne, res, new_color, current_col, i, ax):
    time = int(ligne[-1])
    nbGroup = int(ligne[8])
    id = int(res[i][9])
    
    col = new_color[current_col]
    
    
    bar = []
    bar.append(float(ligne[1]))
    bar.append(float(ligne[2]))
    bar.append(float(ligne[1]))
    
    barFusGroup = barGroup(id, time, res)
    
    x = []
    x.append(float(ligne[1]))
    x.append(barFusGroup[0])
    
    y = []
    y.append(float(ligne[2]))
    y.append(barFusGroup[1])
    
    z = []
    z.append(time)
    z.append(time)
    
    ax.plot(x, y, z, col, label = str(id) )
    ax.legend()



def barGroup(id, time, res):
    
    sol = []
    for i in range(1,len(res)):

        if len(res[i]) == len(res[1]):
            
            if ((time == int(res[i][-1])) & (int(res[i][0]) == id)):
                
                sol.append(float(res[i][1]))
                sol.append(float(res[i][2]))
    sol.append(time)
    return sol

############### changer le paramètre pour voir un groupe différent ###############

trajectoireBarycentre(int(args["groupid"]))

##################################################################################


file.close()
