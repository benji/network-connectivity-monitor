from pandas import read_csv
from matplotlib import pyplot

import plotly.express as px

df = read_csv('data.csv', header=0, parse_dates=True, squeeze=True)
# print(df.head())

fig = px.scatter(x=df.datetime, y=df.dns)
fig.add_scatter(x=df.datetime, y=df.ping)
fig.show()