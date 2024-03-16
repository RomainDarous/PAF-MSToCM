import matplotlib.pyplot as plt
import csv
import numpy as np
import os
from mpl_toolkits import mplot3d
import matplotlib.colors as colors
import math
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


def afficheConvex(idgroup):

    res=[]
    X=[]
    Y=[]
    X2 = []



    for ligne in fichier:
        res.append(ligne)

    for i in range(1,len(res)):
        if len(res[i]) == len(res[1]):
            if int(res[i][0]) == idgroup :
                d0  = ((float(res[i][1])-float(res[i][3]))**2)+((float(res[i][2])-float(res[i][4]))**2)
                d = math.sqrt(d0)
                X.append(d)
                Y.append(int(res[i][-1]))



    Z = getTime(res, idgroup)


    for i in Z:
        X2.append(X[i-1])


    plt.scatter(Z, X2)

    plt.plot(Y,X)

    plt.xlabel("Temps (s)")
    plt.ylabel("Distance entre les barycentres (pixels) ")


    plt.title("Distance entre le barycentre du groupe " + str(idgroup) + " et le barycentre uniquement de son enveloppe convexe en fonction du temps")
    plt.show()


def getTime(res, idgroup):
    Z=[]
    iter = 0

    for i in range(1,len(res)):
        ligne = res[i]
        if iter ==2:
            if (ligne[0] == str(idgroup))&(len(ligne) == len(res[0])):


                if int(ligne[7]) > nb:

                    Z.append(int(ligne[-1]))
                    nb = int(ligne[7])

                elif int(ligne[7]) < nb:

                    Z.append(int(ligne[-1]))
                    nb = int(ligne[7])

        elif iter==1:
            nb=int(ligne[7])
            iter=2


        else:
            iter=1

    return Z

############### changer le paramètre pour voir un groupe différent ###############

afficheConvex(int(args["groupid"]))

##################################################################################


file.close()
