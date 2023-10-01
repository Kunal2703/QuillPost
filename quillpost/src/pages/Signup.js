import { Card, CardBody, CardHeader, Container, Input, Label, Form, FormGroup, Button, Row, Col } from 'reactstrap';
import Base from '../components/Base';
const Signup = () => {
    return (
        <Base>
            <Container>
                <Row className="mt-4">
                    <Col sm={{ size: 6, offset: 3}}>
                        <Card color = "dark" inverse>
                            <CardHeader>

                                <h3>Fill Information to register</h3>

                            </CardHeader>
                            <CardBody>
                                {/* Create Form */}

                                <Form>
                                    {/*Name Filed*/}
                                    <FormGroup>
                                        <Label for="name">Enter Name</Label>
                                        <Input type="text" placeholder='Enter Name' id="name"></Input>
                                    </FormGroup>

                                    {/*email Filed*/}
                                    <FormGroup>
                                        <Label for="email">Enter email</Label>
                                        <Input type="email" placeholder='Enter Email' id="email"></Input>
                                    </FormGroup>

                                    {/*password Filed*/}
                                    <FormGroup>
                                        <Label for="password">Enter Password</Label>
                                        <Input type="password" placeholder='Enter Password' id="password"></Input>
                                    </FormGroup>

                                    {/*about Filed*/}
                                    <FormGroup>
                                        <Label for="about">Enter About</Label>
                                        <Input type="textarea" placeholder='Enter here' id="about" style={{ height: "250px" }}></Input>
                                    </FormGroup>

                                    <Container className="text-center">
                                        <Button outline color='light' >
                                            Register
                                        </Button>
                                        <Button color='secondary' type='reset' className='ms-2'>
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