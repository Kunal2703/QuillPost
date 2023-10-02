import { Card, Col, Container, Row , CardHeader, CardBody, FormGroup, Input, Label, Form, Button} from 'reactstrap';
import Base from '../components/Base';
const Login = () => {
    return (
        <Base>
            
            <Container>

                <Row className="mt-4">

                    <Col sm = { 
                        {
                            size:6,
                            offset:3
                        }
                    }>

                        <Card color = "dark" inverse>
                            
                            <CardHeader className='text-center'>
                                <h3> Login Here !!</h3>
                            </CardHeader>
                            
                            <CardBody>
                                <Form>
                                    {/*Email Field */}

                                    <FormGroup>
                                        <Label for = "username">Enter Email</Label>
                                        
                                        <Input type = "text" id = "username" placeholder = "Enter Your User Name" required></Input>

                                    </FormGroup>

                                    {/*password Field */}

                                    <FormGroup>
                                        <Label for = "password">Enter Password</Label>
                                        
                                        <Input type = "password" id = "password" placeholder='Enter Your Password' required></Input>

                                    </FormGroup>

                                    <Container className="text-center">
                                        <Button color='secondary' className='mt-4'>Login</Button>
                                        <Button className='ms-3 mt-4'color = "secondary">Reset</Button>
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

export default Login;