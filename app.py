from flask import Flask, render_template, session, redirect, request
import mysql.connector

app = Flask(__name__)

app.config['SECRET_KEY'] = 'verysecret'

db = mysql.connector.connect(host='127.0.0.1', user='root', password='', database='blackjack')
cur = db.cursor()

@app.route('/')
def index():
    if 'username' in session:
        return render_template('index.html', username=session['username'])
    
    return render_template('index.html')


@app.route('/register', methods=['POST','GET'])
def register():
    if 'username' in session:
        return redirect('/')
    
    if request.method == 'POST':
        username = request.form['username']
        pwd = request.form['password']
        cur.execute('SELECT * FROM players WHERE username = %s', (username,))
        if cur.fetchone() is not None:
            return render_template('error.html', error='username already exists')
        else:
            cur.execute('INSERT INTO players VALUES (%s,%s,%s,%s)', (username,pwd,0,0,))
            db.commit()
            session['username'] = username
            return redirect('/')
    else:
        return render_template('register.html')


@app.route('/login', methods=['POST','GET'])
def login():
    if 'username' in session:
        return redirect('/')
    
    if request.method == 'POST':
        username = request.form['username']
        pwd = request.form['password']
        cur.execute('SELECT * FROM players WHERE username = %s AND password = %s', (username,pwd,))
        if cur.fetchone() is not None:
            session['username'] = username
            return redirect('/')
        else:
            return render_template('error.html', error='username does not exist')
    else:
        return render_template('login.html')




@app.route('/logout')
def logout():
    session.clear()
    return redirect('/')


if __name__ == '__main__':
    app.run()