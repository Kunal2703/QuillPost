import React, { useEffect, useRef, useState } from 'react'
import { Button, Card, CardBody, Container, Form, Input, Label } from 'reactstrap'
import { loadAllCategories } from '../services/category-service'

import JoditEditor from 'jodit-react';



const AddPost = () => {

    const editor = useRef(null)
    const [content, setContent] = useState('')

    const [categories, setCategories] = useState([])

    const config = {
        placeholder : "Start typing..."
    }

    useEffect(
        () => {
            loadAllCategories().then((data) => {
                console.log(data)
                setCategories(data)
            }).catch(error => {
                console.log(error)
            })
        },
        []
    )


    return (
        <div className="wrappper">


            <Card className="shadow-sm border-0 mt-5" >

                <CardBody>

                    <h3>What going in your mind?</h3>

                        <Form>

                            <div className='my-3'>
                                <Label for="title" >Post Title</Label>
                                <Input 
                                    type="text" 
                                    id="title" 
                                    placeholder="Enter Your Post Title" 
                                    className="rounded-0"
                                />
                            </div>


                            <div className='my-3'>
                                <Label for="content" >Post Content</Label>
                                    {/* <Input 
                                        type="textarea" 
                                        id="content" 
                                        placeholder="Enter Your Post Content" 
                                        className="rounded-0"
                                        style={{height: '300px'}}
                                    /> */}

                                    <JoditEditor
                                        ref = {editor}
                                        value = {content}
                                        onChange={newContent => setContent(newContent)}
                                    />
                            </div>


                            <div className='my-3'>
                                <Label for="category" >Post Category</Label>
                                <Input 
                                    type="select" 
                                    id="category" 
                                    placeholder="Enter Your Post category" 
                                    className="rounded-0"
                                >
                                    {
                                        categories.map((category) => (
                                            <option>
                                                {category.categoryName}
                                            </option>
                                        ))
                                    }

                                </Input>

                            </div>

                            <Container className="text-center">
                                <Button className="rounded-0" color="primary">Create Post</Button>
                                <Button className="rounded-0 ms-3" color="danger">Reset Content</Button>
                            </Container>


                        </Form>
 
                </CardBody>

            </Card>


        </div>
    )
}
export default AddPost