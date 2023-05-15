import {
  Button,
  VStack,
  Box,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
  Flex,
  Spacer,
} from '@chakra-ui/react';
import BoardCard from './BoardCard';
import { AddIcon, ChevronDownIcon } from '@chakra-ui/icons';
import { ListInfo } from './lib/api';

const BoardList = ({ listInfo }: { listInfo: ListInfo }) => {
  return (
    <>
      <Box shadow={'md'} borderRadius={'15'}>
        <Flex
          fontWeight={'semibold'}
          textAlign={'start'}
          mb={'0.5em'}
          align={'center'}
        >
          <Box>{listInfo.title}</Box>
          <Spacer />
          <Menu>
            <MenuButton variant={'ghost'} as={Button}>
              <ChevronDownIcon />
            </MenuButton>
            <MenuList>
              <MenuItem>Delete</MenuItem>
            </MenuList>
          </Menu>
        </Flex>
        <VStack w={'16em'}>
          {listInfo.cards?.map((card) => (
            <BoardCard cardInfo={card} key={card.id} />
          ))}
        </VStack>
        <Button variant={'outline'} w={'100%'} mt={'0.5em'}>
          <AddIcon />
        </Button>
      </Box>
    </>
  );
};

export default BoardList;
