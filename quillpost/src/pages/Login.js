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
                            
                            <CardHeader>
                                <h3> Login Here !!</h3>
                            </CardHeader>
                            
                            <CardBody>
                                <Form>
                                    {/*Email Field */}

                                    <FormGroup>
                                        <Label for = "email">Enter Email</Label>
                                        
                                        <Input type = "text" id = "email" />

                                    </FormGroup>

                                    {/*password Field */}

                                    <FormGroup>
                                        <Label for = "password">Enter Password</Label>
                                        
                                        <Input type = "password" id = "password" />

                                    </FormGroup>

                                    <Container className="text-center">
                                        <Button color='secondary'>Login</Button>
                                        <Button className='ms-2'color = "secondary">Reset</Button>
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