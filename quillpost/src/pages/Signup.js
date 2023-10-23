import { Card, CardBody, CardHeader, Container, Input, Label, Form, FormGroup, Button, Row, Col } from 'reactstrap';
import Base from '../components/Base';
import { useEffect, useState } from 'react';
const Signup = () => {
//----------new-------------
    //create hook

    const [data, setData] = useState({

        username: '',
        firstname: '',
        lastname: '',
        email: '',
        password: '',

    })

    const [error, setError] = useState({
        errors:{},
        isError: false
    })

    useEffect(() => {
        console.log(data);
    }, [data])

    //handle change
    const handleChange = (event, property) => {

            //dynamic setting the values
        setData({...data, [property]:event.target.value})
    }

        //reseting the form
    const resetData = ()=> {
        setData({
            username: '',
            firstname: '',
            lastname: '',
            email: '',
            password: '',
        })
    }

    //submit the form
    const submitForm = (event) => {
        event.preventDefault()

        console.log(data);
        //data validation


        //call server api for sending the data

    }

//----------end-------------
    return (
        <Base>
            <Container>
                <Row className="mt-4">
                    <Col sm={{ size: 6, offset: 3}}>
                        <Card color = "dark" inverse>
                            <CardHeader className="text-center">

                                <h3>SignUp Here !!</h3>

                            </CardHeader>
                            <CardBody>
                                {/* Create Form */}

                                <Form onSubmit={submitForm}>
                                    {/*User Field */}
                                    <FormGroup>
                                        <Label for="username">User Name</Label>
                                        <Input 
                                            type="text" 
                                            placeholder='Choose a unique username' 
                                            id="username" required 
                                            onChange={(e) => handleChange(e, 'username')}
                                            value={data.username}
                                        />
                                    </FormGroup>

                                    {/*First Name Filed*/}
                                    <FormGroup>
                                        <Label for="firstname">Enter First Name</Label>
                                        <Input 
                                            type="text" 
                                            placeholder='Enter Your First Name' 
                                            id="firstname" required
                                            onChange={(e) => handleChange(e, 'firstname')}
                                            value={data.firstname}
                                        />
                                    </FormGroup>

                                    {/*Last Name Filed*/}
                                    <FormGroup>
                                        <Label for="lastname">Enter Last Name</Label>
                                        <Input 
                                            type="text" 
                                            placeholder='Enter Your Last Name' 
                                            id="lastname" required 
                                            onChange={(e) => handleChange(e, 'lastname')}
                                            value={data.lastname}
                                        />
                                    </FormGroup>

                                    {/*email Filed*/}
                                    <FormGroup>
                                        <Label for="email">Enter email</Label>
                                        <Input 
                                            type="email" 
                                            placeholder='name@example.com' 
                                            id="email" required 
                                            onChange={(e) => handleChange(e, 'email')}
                                            value={data.email}
                                        />
                                    </FormGroup>

                                    {/*password Filed*/}
                                    <FormGroup>
                                        <Label for="password">Choose a Password</Label>
                                        <Input 
                                            type="password" 
                                            placeholder='Choose Your password' 
                                            id="password" required 
                                            onChange={(e) => handleChange(e, 'password')}
                                            value={data.password}
                                        />
                                    </FormGroup>

{/*
                                    <FormGroup>
                                        <Label for="password">Confirm Password</Label>
                                        <Input type="password" placeholder='Enter your password again' id="password" required></Input>
                                    </FormGroup>
    */}

                                    <Container className="text-center" >
                                        <Button outline color='light'  className='mt-4'>
                                            Register
                                        </Button>
                                        <Button onClick = {resetData} color='secondary' type='reset' className='ms-3 mt-4'>
                                            Reset
                                        </Button>
                                    </Container>

                                </Form>

                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </Base>
    );
};

export default Signup;